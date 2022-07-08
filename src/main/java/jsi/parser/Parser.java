package jsi.parser;

import jsi.common.Atom;
import jsi.exception.SyntaxError;
import jsi.lexical.Symbols;
import jsi.lexical.Terminator;
import jsi.lexical.Token;
import jsi.lexical.TokenKind;

import java.util.ArrayList;
import java.util.List;

/**
 * 语法分析，构建语法解析树
 * @author AkaneMurakawa
 * @date 2022-06-28
 */
public class Parser {

    /**
     * 记录token下标
     */
    private int current = 0;

    private List<Token> tokens;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    /**
     * 遍历是否结束
     * @return
     */
    private boolean isEnd(){
        int sz = tokens.size();
        return this.current >= sz;
    }

    private Token token(){
        return tokens.get(current);
    }

    /**
     * 识别运算符后会current++, operator则为前一个Token
     * @return
     */
    private Token previous(){
        return tokens.get(current - 1);
    }

    private Expr parsePrimaryExpression(){
        if(Atom.is(TokenKind.NUMBER, token().getTokenKind())){
            // next
            current++;
            return new Literal(previous().getLiteral());
        }
        throw new SyntaxError(String.format("illegal expression %s", token().getLiteral()));
    }

    /**
     * 匹配运算符号
     * @param symbols
     * @return
     */
    private boolean matchSymbols(Symbols... symbols){
        // 结束匹配
        if (isEnd()){
            return false;
        }
        for (Symbols symbol : symbols) {
            if(symbol.is(token().getLiteral())){
                // next
                current++;
                return true;
            }
        }
        return false;
    }

    private boolean isLeftParen(){
        return Atom.is(TokenKind.TERMINATOR, token().getTokenKind()) && Terminator.K_LEFT_PAREN.is(token().getLiteral());
    }

    private boolean isRightParen(){
        return Atom.is(TokenKind.TERMINATOR, token().getTokenKind()) && Terminator.K_RIGHT_PAREN.is(token().getLiteral());
    }

    /**
     * +-操作
     * @return
     */
    private Expr parseExpression(){
        if (isLeftParen()) {
            return parseParentheses();
        }

        // times or divide or percent priority to eval
        Expr expression = parseTerm();
        while (matchSymbols(Symbols.K_PLUS, Symbols.K_MINUS)){
            Token operator = previous();
            Expr right;
            if (isLeftParen()){
                right = parseParentheses();
            }else{
                right = parseTerm();
            }
            expression = new Binary(expression, operator, right);
        }
        return expression;
    }

    /**
     * ()操作
     * @return
     */
    private Expr parseParentheses(){
        // ( next
        current++;
        Expr expression = null;
        while(!isRightParen()){
            expression = parseExpression();
        }
        if (!isRightParen() || expression == null){
            throw new SyntaxError(String.format("missing right paren ')' at %s", token().getLiteral()));
        }
        // ) next
        current++;
        return expression;
    }

    /**
     * *\/%操作
     * @return
     */
    private Expr parseTerm(){
        if (isLeftParen()){
            return parseParentheses();
        }
        Expr expression = parsePrimaryExpression();
        while (matchSymbols(Symbols.K_TIMES, Symbols.K_DIVIDE, Symbols.K_PERCENT)){
            Token operator = previous();
            Expr right;
            if (isLeftParen()){
                right = parseParentheses();
            }else{
                right = parsePrimaryExpression();
            }
            expression = new Binary(expression, operator, right);
        }
        return expression;
    }

    /**
     * 语法分析
     * expression
     *     : term
     *     | expression ADD term
     *     | expression SUB term
     *
     * term
     *     : primary_expression
     *     | term MUL primary_expresion
     *     | term DIV primary_expresion
     *
     * primary_expression
     *     :literal
     * @return
     * @throws SyntaxError
     */
    public List<Expr> parse() throws SyntaxError {
        List<Expr> ast = new ArrayList<>();
        while(!isEnd()){
            Expr expression = parseExpression();
            // ()操作处理，标记为left
            while (matchSymbols(Symbols.K_PLUS, Symbols.K_MINUS)){
                Token operator = previous();
                Expr right = parseTerm();
                expression = new Binary(expression, operator, right);
            }
            while (matchSymbols(Symbols.K_TIMES, Symbols.K_DIVIDE, Symbols.K_PERCENT)){
                Token operator = previous();
                Expr right = parsePrimaryExpression();
                expression = new Binary(expression, operator, right);
            }
            ast.add(expression);
        }
        return ast;
    }
}
