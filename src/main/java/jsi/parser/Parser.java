package jsi.parser;

import jsi.ast.LexicalType;
import jsi.ast.Token;
import jsi.exception.SyntaxError;

import java.util.ArrayList;
import java.util.List;

/**
 * 语法分析
 * @author AkaneMurakawa
 * @date 2022-06-28
 */
public class Parser {

    public static List<Node> parse(List<Token> tokenizer) throws SyntaxError {
        List<Node> ast = new ArrayList<>();
        int i = 0;
        int sz = tokenizer.size();
        while(i < sz){
            Token token = tokenizer.get(i);
            if (LexicalType.SYMBOLS.equals(token.getType())){
                int pre = i-1;
                int next = i+1;
                if (pre < 0 || next >= sz){
                    throw new SyntaxError("operator format error");
                }
                if (!LexicalType.NUMBER.equals(tokenizer.get(pre).getType()) || !LexicalType.NUMBER.equals(tokenizer.get(next).getType())){
                    throw new SyntaxError("operating parameters must be numeric");
                }
                ast.add(Node.build(NodeType.EXPRESSION, token.getValue(), tokenizer.get(pre).getValue(), tokenizer.get(next).getValue()));
                i++;
                continue;
            }
            i++;
        }
        return ast;
    }
}
