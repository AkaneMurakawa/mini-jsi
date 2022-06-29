package jsi.parser;

import jsi.lexical.Token;

/**
 * Binary
 *
 * @author AkaneMurakawa
 * @date 2022-06-29
 */
public class Binary implements Expr{

    private Expr left;

    private Token operator;

    private Expr right;

    public Binary(Expr left, Token operator, Expr right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public Expr getLeft() {
        return left;
    }

    public Token getOperator() {
        return operator;
    }

    public Expr getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Binary{" +
                "left=" + left +
                ", operator=" + operator +
                ", right=" + right +
                '}';
    }
}
