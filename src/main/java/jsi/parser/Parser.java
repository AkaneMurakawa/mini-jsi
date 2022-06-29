package jsi.parser;

import jsi.common.Atom;
import jsi.exception.SyntaxError;
import jsi.lexical.LiteralType;
import jsi.lexical.Symbols;
import jsi.lexical.Terminator;
import jsi.lexical.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * 语法分析
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

    private Expr literal(){
        if(Atom.is(LiteralType.NUMBER, token().getLiteralType())){
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
        return Atom.is(LiteralType.TERMINATOR, token().getLiteralType()) && Terminator.K_LEFT_PAREN.is(token().getLiteral());
    }

    private boolean isRightParen(){
        return Atom.is(LiteralType.TERMINATOR, token().getLiteralType()) && Terminator.K_RIGHT_PAREN.is(token().getLiteral());
    }

    /**
     * +-操作
     * @return
     */
    private Expr plusOrMinus(){
        if (isLeftParen()){
            return paren();
        }

        // times or divide or percent priority to eval
        Expr expression = priority();
        while (matchSymbols(Symbols.K_PLUS, Symbols.K_MINUS)){
            Token operator = previous();
            Expr right;
            if (isLeftParen()){
                right = paren();
            }else{
                right = priority();
            }
            expression = new Binary(expression, operator, right);
        }
        return expression;
    }

    /**
     * ()操作
     * @return
     */
    private Expr paren(){
        // ( next
        current++;
        Expr expression = null;
        while(!isRightParen()){
            expression = plusOrMinus();
        }
        if (!isRightParen() || expression == null){
            throw new SyntaxError(String.format("missing right paren ')' at %s", token().getLiteral()));
        }
        // ) next
        current++;
        while (matchSymbols(Symbols.K_PLUS, Symbols.K_MINUS)){
            Token operator = previous();
            Expr right = priority();
            expression = new Binary(expression, operator, right);
        }
        while (matchSymbols(Symbols.K_TIMES, Symbols.K_DIVIDE, Symbols.K_PERCENT)){
            Token operator = previous();
            Expr right = literal();
            expression = new Binary(expression, operator, right);
        }
        return expression;
    }

    /**
     * *\/操作
     * @return
     */
    private Expr priority(){
        if (isLeftParen()){
            return paren();
        }
        Expr expression = literal();
        while (matchSymbols(Symbols.K_TIMES, Symbols.K_DIVIDE, Symbols.K_PERCENT)){
            Token operator = previous();
            Expr right;
            if (isLeftParen()){
                right = paren();
            }else{
                right = literal();
            }
            expression = new Binary(expression, operator, right);
        }
        return expression;
    }

    /**
     * 语法分析
     * @return
     * @throws SyntaxError
     */
    public List<Expr> parse() throws SyntaxError {
        List<Expr> ast = new ArrayList<>();
        while(!isEnd()){
            ast.add(plusOrMinus());
        }
        return ast;
    }
}
