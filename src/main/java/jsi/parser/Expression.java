package jsi.parser;

import jsi.ast.Symbols;
import jsi.exception.ParserException;

import java.util.List;

/**
 * 表达式
 * @author AkaneMurakawa
 * @date 2022-06-28
 */
public class Expression {

    public static Object eval(List<Node> ast) throws ParserException {
        Double result = null;
        int i = 0;
        int sz = ast.size();
        while(i < sz){
            Node node = ast.get(i);
            if (NodeType.EXPRESSION.equals(node.getType())){
                Symbols symbols = Symbols.get(node.getOperation());
                Double left = Double.parseDouble(node.getLeft());
                Double right = Double.parseDouble(node.getRight());
                if (result != null){
                    left = result;
                }
                // TODO 优先级
                switch (symbols){
                    case K_PLUS:
                        result = left + right;
                        break;
                    case K_MINUS:
                        result = left - right;
                        break;
                    case K_TIMES:
                        result = left * right;
                        break;
                    case K_DIVIDE:
                        result = left / right;
                        break;
                    case K_PERCENT:
                        result = left % right;
                        break;
                    default:
                        throw new ParserException("unsupported operation");
                }
                i++;
                continue;
            }
            i++;
        }
        return result;
    }
}
