package jsi.ast;

/**
 * 正则表达式
 * @author AkaneMurakawa
 * @date 2022-06-27
 */
public interface Pattern {

    /**
     * 字符
     */
    String LETTERS = "^[a-z A-Z]$";

    /**
     * 数字
     */
    String NUMBER = "-?[0-9]+(\\.[0-9]+)?";

}
