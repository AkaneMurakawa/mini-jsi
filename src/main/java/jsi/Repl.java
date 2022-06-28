package jsi;

import jsi.ast.Lexical;
import jsi.ast.Token;
import jsi.exception.ParserException;
import jsi.exception.SyntaxError;
import jsi.parser.Expression;
import jsi.parser.Node;
import jsi.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * REPL: Read-Eval-Print Loop
 *
 * @author AkaneMurakawa
 * @date 2022-06-27
 */
public class Repl {
    /**
     * 提示符
     */
    private static final String PROMPT = ">> ";

    public static void main(String[] args) throws IOException {
        while(true){
            System.out.print(PROMPT);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line = reader.readLine();

            try{
                System.out.println(line);
                List<Token> tokenizer = Lexical.tokenizer(line);
                tokenizer.forEach(System.out::println);

                List<Node> ast = Parser.parse(tokenizer);
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
