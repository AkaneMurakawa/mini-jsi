package jsi.interpreter;

import jsi.exception.ParserException;
import jsi.lexical.Symbols;
import jsi.parser.Binary;
import jsi.parser.Expr;
import jsi.parser.Literal;

import java.util.List;

/**
 * 表达式
 * @author AkaneMurakawa
 * @date 2022-06-28
 */
public class Expression {

    public static Object eval(List<Expr> ast) throws ParserException {
        int i = 0;
        int sz = ast.size();
        Double result = 0.0;
        while(i < sz){
            Expr expr = ast.get(i);
            result += Double.parseDouble(evaluate(expr).toString());
            i++;
            continue;
        }
        return result;
    }

    private static Object evaluate(Expr expr) throws ParserException {
        if (expr instanceof Binary){
            String operator = ((Binary) expr).getOperator().getLiteral();
            Symbols symbols = Symbols.get(operator);
            if (symbols == null){
                throw new ParserException(String.format("unsupported operation %s", operator));
            }
            Double result;
            Expr leftExpr = ((Binary) expr).getLeft();
            Expr rightExpr = ((Binary) expr).getRight();
            // 递归计算
            Double left = Double.parseDouble(evaluate(leftExpr).toString());
            Double right = Double.parseDouble(evaluate(rightExpr).toString());
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
                    throw new ParserException(String.format("unsupported operation %s", operator));
            }
            return result;
        }
        if (expr instanceof Literal){
            return ((Literal) expr).getValue();
        }
        throw new ParserException("illegal expression");
    }
}
