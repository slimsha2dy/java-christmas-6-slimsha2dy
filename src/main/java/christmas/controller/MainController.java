package christmas.controller;

import java.util.Map;

import christmas.view.*;
import christmas.model.*;

public class MainController {
    private OrderMenu orderMenu;

    public MainController() {
        orderMenu = new OrderMenu();
    }

    public void run() {
        orderMenu.setOrderDate(InputView.readDate());
        setReadOrder();

        OutputView.printPreview(orderMenu.getOrderDate());
        OutputView.printOrder(orderMenu.getUserOrder());
        int totalPrice = orderMenu.getTotalPrice();
        OutputView.printTotalPrice(totalPrice);
        OutputView.printPresent(totalPrice);
        benefitControl(totalPrice);
    }

    private void benefitControl(int totalPrice) {
        Benefit benefit = new Benefit(orderMenu.getOrderDate(), orderMenu.getCategoryCount(), totalPrice);
        OutputView.printBenefits(benefit.getBenefits());
    }

    private void setReadOrder() {
        Map<String, Integer> orderMenu;
        try {
            orderMenu = InputView.readOrder();
            this.orderMenu.setUserOrder(orderMenu);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            setReadOrder();
        }
    }
}
