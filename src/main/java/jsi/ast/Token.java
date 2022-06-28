package jsi.ast;

/**
 * token
 *
 * @author AkaneMurakawa
 * @date 2022-06-27
 */
public class Token {

    private String type;

    private String value;

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    static Token build(String type, String value){
        Token token = new Token();
        token.type = type;
        token.value = value;
        return token;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
