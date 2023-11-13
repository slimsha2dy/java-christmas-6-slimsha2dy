package christmas.controller;

import java.util.Map;

import christmas.view.*;

public class MainController {

    public MainController() {
    }

    public void run() {
        int date = InputView.readDate();
        Map<String, Integer> order = InputView.readOrder();
    }
}
