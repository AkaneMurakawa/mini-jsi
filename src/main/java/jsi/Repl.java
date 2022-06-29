package jsi;

import jsi.exception.ParserException;
import jsi.exception.SyntaxError;
import jsi.interpreter.Expression;
import jsi.lexical.Lexical;
import jsi.lexical.Token;
import jsi.parser.Expr;
import jsi.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * REPL: Read-Eval-Print Loop
 * 简单解释器基本结构
 * Lexical ———Token———> Parser ———AST———> Interpreter
 * @author AkaneMurakawa
 * @date 2022-06-27
 */
public class Repl {
    /**
     * 提示符
     */
    private static final String PROMPT = ">> ";
    /**
     * 提示符
     */
    private static String INFO = String.format("JSI %s %s %s %s",
            "0.0.1",
            "Java script interpreter",
            System.getProperty("os.name"),
            System.getProperty("os.arch"));

    public static void main(String[] args) throws IOException {
        System.out.println(INFO);
        while(true){
            System.out.print(PROMPT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();

            try{
                System.out.println(line);
                List<Token> tokens = Lexical.tokenizer(line);
                System.out.println("[debug] token: ");
                tokens.forEach(System.out::println);

                List<Expr> ast = new Parser(tokens).parse();
                System.out.println("[debug] ast: ");
                ast.forEach(System.out::println);

                Object data = Expression.eval(ast);
                System.out.println(data);
            }catch (ParserException e){
                System.out.println(String.format("eval [%s] error [%s]", line, e.getMessage()));
            }catch (SyntaxError e){
                System.out.println(String.format("line [%s] syntax error [%s]", line, e.getMessage()));
            }
        }
    }
}
