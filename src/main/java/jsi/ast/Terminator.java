package jsi.ast;

/**
 * 终止符
 * @author AkaneMurakawa
 * @date 2022-06-28
 */
public enum Terminator implements Element{

    K_LEFT_BRACE("{"),
    K_LEFT_BRACKET("["),
    K_LEFT_PAREN("("),
    K_RIGHT_BRACE("}"),
    K_RIGHT_BRACKET("]"),
    K_RIGHT_PAREN(")"),
    K_DOT("."),
    ;

    private final String keyword;

    Terminator(String keyword) {
        this.keyword = keyword;
    }

    public static boolean contains(String keyword){
        Terminator[] values = values();
        for (Terminator value : values) {
            if (value.getKeyword().equals(keyword)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getKeyword() {
        return this.keyword;
    }
}
