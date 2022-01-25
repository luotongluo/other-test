package com.lt.dailytest.other;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author tong.luo
 * @description
 * @date 2022/1/24 16:28
 */
public class SystemIn {
    @Test
    public void sannerIn() {
        System.out.println("please input num : ");
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        System.out.println("input num : " + s);
        input.close();
    }
    @Test
    public void bufferIn() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String s = input.readLine();
    }
}
