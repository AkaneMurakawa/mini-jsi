package jsi.exception;

/**
 * 语法错误
 * @author AkaneMurakawa
 * @date 2022-06-27
 */
public class SyntaxError extends Error {
    public SyntaxError(String message) {
        super(message);
    }
}
