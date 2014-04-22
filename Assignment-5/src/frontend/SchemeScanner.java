package frontend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Scans a Scheme source by each token.
 *
 * @author BrandonRossi
 * @author Christopher Raleigh
 */
public class SchemeScanner {

    private final String keyword = "KEYWORD";
    private final String identifier = "IDENTIFIER";
    private final String special_Symbol = "SPECIAL_SYMBOL";
    private final String procedure = "PROCEDURE";
    private final String scope_Keyword = "SCOPE_KEYWORD";
    Scanner fileScanner;
    HashMap<String, String> key_Word_Map;
    HashMap<String, String> special_Symbol_Map;
    HashMap<String, String> procedure_Symbol_Map;
    HashMap<String, String> scope_Keyword_Map;

    /**
     *
     * @param file the file to scan
     * @throws FileNotFoundException
     */
    public SchemeScanner(File file) throws FileNotFoundException {
        fileScanner = new Scanner(file);
        key_Word_Map = setUpKeywordMap();
        special_Symbol_Map = setUpSpecialSymbolMap();
        procedure_Symbol_Map = setUpProcedureSymbolMap();
        scope_Keyword_Map = setUpScopeKeyWordMap();
    }

    /**
     *
     * @param file the string to scan
     */
    public SchemeScanner(String file) {
        fileScanner = new Scanner(file);
        key_Word_Map = setUpKeywordMap();
        special_Symbol_Map = setUpSpecialSymbolMap();
        procedure_Symbol_Map = setUpProcedureSymbolMap();
    }

    /**
     *
     * @return the next token in the source
     */
    public Token nextToken() {
        try {
            String currentToken = fileScanner.next();

            if (currentToken.equals("(")) {
                return new Token("(", Token.Type.SPECIAL_SYMBOL);
            } else if (currentToken.equals(")")) {
                return new Token(")", Token.Type.SPECIAL_SYMBOL);
            } else if (is_Key_Word(currentToken)) {
                return new Token(currentToken, Token.Type.KEYWORD);
            } else if (is_Procedure(currentToken)) {
                return new Token(currentToken, Token.Type.PROCEDURE);
            } else if (is_Special_Symbol(currentToken)) {
                return new Token(currentToken, Token.Type.SPECIAL_SYMBOL);
            } else if (is_Scope_Keyword(currentToken))
            {
                return new Token(currentToken, Token.Type.SCOPE_KEYWORD);
            }
            else if (currentToken.equals("'(")) {
                String tokenName = currentToken;
                while (!currentToken.equals(")")) {
                    currentToken = fileScanner.next();
                    tokenName += currentToken + " ";
                }
                int index = tokenName.lastIndexOf(" ) ");
                if (index != -1) {
                    tokenName = tokenName.substring(0, index) + ")";
                } else {
                    tokenName = tokenName.substring(0, tokenName.length() - 2) + ")";
                }
                return new Token(tokenName, Token.Type.IDENTIFIER);
            } else if (Character.isDigit(currentToken.charAt(0))) {
                if(currentToken.length() == 1)
                {
                    return new Token(currentToken, Token.Type.NUMBER);
                }
                else
                {
                    for (int i = 1; i < currentToken.length(); i++)
                    {
                        if(!Character.isDigit(currentToken.charAt(i)) && currentToken.charAt(i) != '.')
                        {
                            return new Token(currentToken, Token.Type.ERROR);
                        }
                    }
                }
            }
            return new Token(currentToken, Token.Type.IDENTIFIER);
        } catch (NoSuchElementException e) {
            return new Token("END OF INPUT", Token.Type.END_OF_INPUT);
        }
    }

    /**
     * Checks if a string is a procedure.
     *
     * @param key the string to check
     * @return true if key is a procedure
     */
    private boolean is_Procedure(String key) {
        return procedure_Symbol_Map.containsKey(key);
    }

    /**
     * Checks if a string is a special symbol.
     *
     * @param key the string to check
     * @return true if key is a special symbol
     */
    private boolean is_Special_Symbol(String key) {
        return special_Symbol_Map.containsKey(key);
    }

    /**
     * Checks if a string is a keyword.
     *
     * @param key the string to check
     * @return true if key is a keyword
     */
    private boolean is_Key_Word(String key) {
        return key_Word_Map.containsKey(key);
    }

    private boolean is_Scope_Keyword(String key)
    {
        return scope_Keyword_Map.containsKey(key);
    }

    /**
     *
     * @return a map of all of the keywords in Scheme
     */
    private HashMap<String, String> setUpKeywordMap() {
        HashMap<String, String> newMap = new HashMap<>();
        newMap.put("and", keyword);
        newMap.put("begin", keyword);
        newMap.put("begin0", keyword);
        newMap.put("break-var", keyword);
        newMap.put("case", keyword);
        newMap.put("cond", keyword);
        newMap.put("cycle", keyword);

        newMap.put("delay", keyword);
        newMap.put("delay-list-cons", keyword);
        newMap.put("do", keyword);
        newMap.put("else", keyword);
        newMap.put("extend-syntax", keyword);
        newMap.put("for", keyword);
        newMap.put("freeze", keyword);
        newMap.put("if", keyword);


        newMap.put("macro", keyword);
        newMap.put("object-maker", keyword);
        newMap.put("or", keyword);
        newMap.put("quote", keyword);
        newMap.put("repeat", keyword);
        newMap.put("safe-letrec", keyword);
        newMap.put("set!", keyword);
        newMap.put("stream-cons", keyword);
        newMap.put("variable-case", keyword);
        newMap.put("while", keyword);
        newMap.put("wrap", keyword);
        return newMap;
    }

    private HashMap<String, String> setUpScopeKeyWordMap()
    {
        HashMap<String, String> newMap = new HashMap<>();
        newMap.put("define", scope_Keyword);
        newMap.put("let*", scope_Keyword);
        newMap.put("let", scope_Keyword);
        newMap.put("letrec", scope_Keyword);
        newMap.put("lambda", scope_Keyword);
        return newMap;
    }

    /**
     *
     * @return a map of all of the special symbols in Scheme
     */
    private HashMap<String, String> setUpSpecialSymbolMap() {
        HashMap<String, String> newMap = new HashMap<>();
        newMap.put("[", special_Symbol);
        newMap.put("]", special_Symbol);
        newMap.put("{", special_Symbol);
        newMap.put("}", special_Symbol);
        newMap.put(";", special_Symbol);
        newMap.put(",", special_Symbol);
        newMap.put(".", special_Symbol);
        newMap.put("\"", special_Symbol);
        newMap.put("\'", special_Symbol);
        newMap.put("#", special_Symbol);
        newMap.put("\\", special_Symbol);
        return newMap;
    }

    /**
     *
     * @return a map of all of the predefined procedures in Scheme
     */
    private HashMap<String, String> setUpProcedureSymbolMap() {
        HashMap<String, String> newMap = new HashMap<>();
        newMap.put("+", procedure);
        newMap.put("-", procedure);
        newMap.put("*", procedure);
        newMap.put("/", procedure);
        newMap.put("null?", procedure);
        newMap.put("member?", procedure);
        newMap.put("list?", procedure);
        newMap.put("not", procedure);
        newMap.put("symbol?", procedure);
        newMap.put("cons", procedure);
        newMap.put("map", procedure);
        return newMap;
    }
}
