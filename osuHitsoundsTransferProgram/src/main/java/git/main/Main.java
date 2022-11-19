package git.main;


import gui.OHSTP;

import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author waougri
 */

public class Main {
    public static void main(String[] args) {
        try {
            directory.fileParserUnit.CreateTempFiles("src");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            directory.fileParserUnit.CreateTempFiles("trgt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        OHSTP.guiInit();


    }
}
