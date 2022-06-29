package jsi.lexical;

/**
 * token
 *
 * @author AkaneMurakawa
 * @date 2022-06-27
 */
public class Token {

    /**
     * 标识符类型
     */
    private String literalType;
    /**
     * 标识符值
     */
    private String literal;

    public String getLiteralType() {
        return literalType;
    }

    public String getLiteral() {
        return literal;
    }

    public Token(String literalType, String literal) {
        this.literalType = literalType;
        this.literal = literal;
    }

    @Override
    public String toString() {
        return "Token{" +
                "literalType='" + literalType + '\'' +
                ", literal='" + literal + '\'' +
                '}';
    }
}
