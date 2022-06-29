package jsi.lexical;

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
        List<Token> tokens = new ArrayList<>();

        int i = 0;
        int sz = chars.length;
        while(i < sz){
            char c = chars[i];
            boolean hasNext = i+1 < sz;
            String str = String.valueOf(c);
            String strs;
            // 多字符串操作符
            if (hasNext && Symbols.contains((strs = str.concat(String.valueOf(chars[i+1]))))){
                tokens.add(new Token(LiteralType.SYMBOLS, strs));
                i++;i++;
                continue;
            }
            // 终止符
            if (Terminator.contains(str)){
                tokens.add(new Token(LiteralType.TERMINATOR, str));
                i++;
                continue;
            }
            // 数字
            if (str.matches(Pattern.NUMBER)){
                String value = "";
                while(i < sz && (str = String.valueOf(chars[i])).matches(Pattern.NUMBER)){
                    value += str;
                    // 小数点匹配
                    if (i+1 < sz && Terminator.K_DOT.is((str = String.valueOf(chars[i+1])))){
                        value += str;
                        i++;
                    }
                    i++;
                }
                if (!value.matches(Pattern.NUMBER)){
                    throw new ParserException(String.format("illegal number %s", value));
                }
                tokens.add(new Token(LiteralType.NUMBER, value));
                continue;
            }

            // 负数开始
            if (i == 0 && Symbols.K_MINUS.is(str)){
                String value = str;
                while(i < sz && (str = String.valueOf(chars[i+1])).matches(Pattern.NUMBER)){
                    value += str;
                    if (i+1 < sz && Terminator.K_DOT.is((str = String.valueOf(chars[i+1])))){
                        value += str;
                        i++;
                    }
                    i++;
                }
                if (!value.matches(Pattern.NUMBER)){
                    throw new ParserException(String.format("illegal number %s", value));
                }
                tokens.add(new Token(LiteralType.NUMBER, value));
                i++;
                continue;
            }

            // 单字符操作符
            if (Symbols.contains(str)){
                tokens.add(new Token(LiteralType.SYMBOLS, str));
                i++;
                continue;
            }

            // 字母
            if (str.matches(Pattern.LETTERS)){
                String value = "";
                while(i < sz && (str = String.valueOf(chars[i])).matches(Pattern.LETTERS)){
                    value += str;
                    i++;
                }
                tokens.add(new Token(LiteralType.VARIABLE, value));
                continue;
            }

            throw new ParserException(String.format("unknown symbol %s", str));
        }
        return tokens;
    }
}
