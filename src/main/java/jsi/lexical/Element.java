package jsi.lexical;

/**
 * 元素
 * @author AkaneMurakawa
 * @date 2022-06-27
 */
public interface Element {

    /**
     * 获取关键字
     * @return
     */
    String getKeyword();

    /**
     * is
     * @param keyword
     * @return
     */
    default boolean is(String keyword){
        return this.getKeyword().equals(keyword);
    }

}
