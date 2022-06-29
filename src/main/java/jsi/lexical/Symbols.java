package jsi.lexical;

/**
 * 运算符号
 * @author AkaneMurakawa
 * @date 2022-06-27
 */
public enum Symbols implements Element{

    K_PLUS("+"),
    K_MINUS("-"),
    K_TIMES("*"),
    K_DIVIDE("/"),
    K_PERCENT("%"),
    K_DECREASE("-="),
    K_INCREASE("+="),
    K_GREATER(">"),
    K_LESS("<"),
    K_EQUALS("=="),
    K_NOT_EQUALS("!="),
    K_GRATER_OR_EQUALS(">="),
    K_LESS_OR_EQUALS("<="),
    K_AND("&&"),
    K_OR("||"),
    K_NOT("!"),
    ;

    private final String keyword;

    Symbols(String keyword) {
        this.keyword = keyword;
    }

    public static Symbols get(String keyword){
        Symbols[] values = values();
        for (Symbols value : values) {
            if (value.is(keyword)){
                return value;
            }
        }
        return null;
    }

    public static boolean contains(String keyword){
        Symbols[] values = values();
        for (Symbols value : values) {
            if (value.is(keyword)){
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

