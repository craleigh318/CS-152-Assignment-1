package backend;

import intermediate.SchemeList;
import intermediate.SchemeListItem;

/**
 * A class that is used to evaluate list expressions and procedures such as
 * Addition, Subtraction, Multiplication, car, cdr, etc...
 *
 * @author BrandonRossi
 * @author Christopher Raleigh
 */
public class Procedures
{

    /**
     * Calculates the sum.
     *
     * @param list the list to calculate, from the first number
     * @return the sum
     */
    public static int sum (SchemeList list)
    {
        int ret = Integer.parseInt(list.car().toString());
        SchemeList cdr = list.cdr();
        if (cdr != null)
        {
            ret += sum(cdr);
        }
        System.out.printf("The Sum Is: %d", ret);
        return ret;
    }

    /**
     * Calculates the difference.
     *
     * @param list the list to calculate, from the first number
     * @return the sum
     */
    public static int difference (SchemeList list)
    {
        int ret = Integer.parseInt(list.car().toString());
        SchemeList cdr = list.cdr();
        if (cdr != null)
        {
            ret -= sum(cdr);
        }
        System.out.printf("The Sum Is: %d", ret);
        return ret;
    }

    /**
     * Calculates the product.
     *
     * @param list the list to calculate, from the first number
     * @return the product
     */
    public static double product (SchemeList list)
    {
        double ret = Double.parseDouble(list.car().toString());
        SchemeList cdr = list.cdr();
        if (cdr != null)
        {
            ret *= product(cdr);
        }
        System.out.printf("The Product Is: %d", ret);
        return ret;
    }

    /**
     * Calculates the quotient.
     *
     * @param list the list to calculate, from the first number
     * @return the product
     */
    public static double quotient (SchemeList list)
    {
        double ret = Double.parseDouble(list.car().toString());
        SchemeList cdr = list.cdr();
        if (cdr != null)
        {
            ret /= product(cdr);
        }
        System.out.printf("The Quotient Is: %d", ret);
        return ret;
    }

    /**
     * Returns the first item of the list.
     *
     * @param list the list from which to derive the item
     * @return the first item of the list
     */
    public static SchemeListItem car (SchemeList list)
    {
        System.out.println("The CAR IS: " + list.car().toString());
        return list.car();
    }

    /**
     * Returns the list from the second item.
     *
     * @param list the list to derive
     * @return the list from the second item
     */
    public static SchemeList cdr (SchemeList list)
    {
        System.out.println("The CDR IS: " + list.cdr().toString());
        return list.cdr();
    }
}
