package util;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputUtil {
    @Getter
    private static final InputUtil instance = new InputUtil();
    private final Scanner scanner = new Scanner(System.in);

    private InputUtil() {}

    public byte requiredByte(String title) {
        System.out.print(title);
        return scanner.nextByte();
    }

    public int requiredInt(String title) {
        System.out.print(title);
        return scanner.nextInt();
    }

    public String requiredString(String title) {
        System.out.print(title);
        return scanner.nextLine();
    }

    public double requiredDouble(String title) {
        System.out.print(title);
        return scanner.nextDouble();
    }

    public long requiredLong(String title) {
        System.out.print(title);
        return scanner.nextLong();
    }

    public BigDecimal requiredBigDecimal(String title){
        System.out.println(title);
        return scanner.nextBigDecimal();
    }
}
