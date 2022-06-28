package jsi.ast;

import jsi.exception.ParserException;

import java.util.ArrayList;
import java.util.List;

/**
 * 词法分析
 * @author AkaneMurakawa
 * @date 2022-06-28
 */
public class Lexical {

    /**
     * 解析
     * @param line
     * @return Map<type, value>
     * @throws ParserException
     */
    public static List<Token> tokenizer(String line) throws ParserException {
        char[] chars = line.toCharArray();
        List<Token> tokenizer = new ArrayList<>();

        int i = 0;
        int sz = chars.length;
        while(i < sz){
            char c = chars[i];
            boolean hasNext = i+1 < sz;
            String str = String.valueOf(c);
            String strs;
            // 多字符串
            if (hasNext && Symbols.contains((strs = str.concat(String.valueOf(chars[i+1]))))){
                tokenizer.add(Token.build(LexicalType.SYMBOLS, strs));
                i++;i++;
                continue;
            }
            // 单字符串
            if (Symbols.contains(str)){
                tokenizer.add(Token.build(LexicalType.SYMBOLS, str));
                i++;
                continue;
            }
            if (Terminator.contains(str)){
                tokenizer.add(Token.build(LexicalType.TERMINATOR, str));
                i++;
                continue;
            }

            // 字符
            if (str.matches(Pattern.LETTERS)){
                String value = "";
                while(i < sz && (str = String.valueOf(chars[i])).matches(Pattern.LETTERS)){
                    value += str;
                    i++;
                }
                tokenizer.add(Token.build(LexicalType.VARIABLE, value));
                continue;
            }

            // 数字
            if (str.matches(Pattern.NUMBER)){
                String value = "";
                while(i < sz && (str = String.valueOf(chars[i])).matches(Pattern.NUMBER)){
                    value += str;
                    if (i+1 < sz && Terminator.K_DOT.getKeyword().equals((str = String.valueOf(chars[i+1])))){
                        value += str;
                        i++;
                    }
                    i++;
                }
                if (!value.matches(Pattern.NUMBER)){
                    throw new ParserException(String.format("illegal number %s", value));
                }
                tokenizer.add(Token.build(LexicalType.NUMBER, value));
                continue;
            }

            throw new ParserException(String.format("unknown symbol %s", str));
        }
        return tokenizer;
    }
}
