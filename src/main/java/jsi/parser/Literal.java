package jsi.parser;

/**
 * Literal
 *
 * @author AkaneMurakawa
 * @date 2022-06-29
 */
public class Literal implements Expr{

    private Object value;

    public Literal(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
