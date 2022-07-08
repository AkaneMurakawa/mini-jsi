package jsi.lexical;

/**
 * token
 *
 * @author AkaneMurakawa
 * @date 2022-06-27
 */
public class Token {

    /**
     * 标识符种类
     */
    private String tokenKind;
    /**
     * 标识符值
     */
    private String literal;

    public String getTokenKind() {
        return tokenKind;
    }

    public String getLiteral() {
        return literal;
    }

    public Token(String tokenKind, String literal) {
        this.tokenKind = tokenKind;
        this.literal = literal;
    }

    @Override
    public String toString() {
        return "Token{" +
                "tokenKind='" + tokenKind + '\'' +
                ", literal='" + literal + '\'' +
                '}';
    }
}
