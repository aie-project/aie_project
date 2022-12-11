import java.util.Scanner;
public class Class__1_Analysis {
    Class_1_Parameters Parameters = new Class_1_Parameters();
    Class_2_GetInfo GetInfo = new Class_2_GetInfo();
    public double[] FuncAnalysis() {
        int NumberOfCandlesForAnalysis = Parameters.NumberOfCandlesForAnalysis;
        if (Parameters.NumberOfCandlesForAnalysis > Parameters.PotentialMasForAnalysis.length) {
            NumberOfCandlesForAnalysis = Parameters.PotentialMasForAnalysis.length;
        }
        double TakeProfitPercentage = Parameters.MinTakeProfitPercentageForAnalysis;
        double ImpulseValueUpPercentage = Parameters.MinImpulseValueUpPercentageForAnalysis;
        double ImpulseValueDownPercentage = Parameters.ImpulseValueDownPercentageForAnalysis;
        double DeviationToStopLossPercentage = Parameters.DeviationToStopLossPercentageForAnalysis;
        double PossibleIncome;
        int DealsInPlus;
        int DealsInMinus;
        double [] BestPossibleIncome = { 0, 0, 0, 0, 0};
        int [] BestDealsInPlus = { 0, 0, 0, 0, 0};
        int []BestDealsInMinus = { 0, 0, 0, 0, 0};
        double [] BestTakeProfitPercentage = { 0, 0, 0, 0, 0};
        double [] BestImpulseValueUpPercentage = { 0, 0, 0, 0, 0};
        int [] DealMissed = { 0, 0, 0, 0, 0};
        while (ImpulseValueUpPercentage <= Parameters.MaxImpulseValueUpPercentageForAnalysis) {
            TakeProfitPercentage = Parameters.MinTakeProfitPercentageForAnalysis;
            while (TakeProfitPercentage <= Parameters.MaxTakeProfitPercentageForAnalysis) {
                int i = 0;
                PossibleIncome = 0;
                DealsInPlus = 0;
                DealsInMinus = 0;
                while (i < NumberOfCandlesForAnalysis) {
                    if (ImpulseValueUpPercentage <= Parameters.PotentialMasForAnalysis[i]) {
                        if ((ImpulseValueUpPercentage + TakeProfitPercentage) <= Parameters.PotentialMasForAnalysis[i]) {
                            PossibleIncome = PossibleIncome - Parameters.CommissionForAnalysis + TakeProfitPercentage;
                            DealsInPlus++;
                        } else {
                            if (Parameters.ChangeMasForAnalysis[i] >= ImpulseValueUpPercentage) {
                                PossibleIncome = PossibleIncome - Parameters.CommissionForAnalysis + (Parameters.ChangeMasForAnalysis[i]-ImpulseValueUpPercentage);
                                DealsInPlus++;
                            } else {
                                if (Parameters.ChangeMasForAnalysis[i] >= 0) {
                                    PossibleIncome = PossibleIncome - Parameters.CommissionForAnalysis - (ImpulseValueUpPercentage-Parameters.ChangeMasForAnalysis[i]);
                                } else {
                                    PossibleIncome = PossibleIncome - Parameters.CommissionForAnalysis - (ImpulseValueUpPercentage+Math.abs(Parameters.ChangeMasForAnalysis[i]));
                                }
                                DealsInMinus++;
                            }
                        }
                    }
                    i++;
                }
                int w = 0;
                while (w<5) {
                    if (BestPossibleIncome [w] < PossibleIncome) {
                        BestPossibleIncome [w] = PossibleIncome;
                        BestDealsInPlus [w] = DealsInPlus;
                        BestDealsInMinus [w] = DealsInMinus;
                        BestImpulseValueUpPercentage [w] = ImpulseValueUpPercentage;
                        BestTakeProfitPercentage [w] = TakeProfitPercentage;
                        break;
                    }
                    w++;
                }
                TakeProfitPercentage = TakeProfitPercentage + 0.1;
            }
            ImpulseValueUpPercentage = ImpulseValueUpPercentage + 0.1;
        }
        int w = 0;
        while (w<5) {
            DealMissed [w] = NumberOfCandlesForAnalysis - BestDealsInPlus [w] - BestDealsInMinus [w];
            w++;
        }
        System.out.print("Тикер: " + Parameters.Ticker + ". ");
        System.out.print("Таймфрейм: " + Parameters.TimeFrame + ". ");
        System.out.println("Вкладываемые средства в сделку: " + Parameters.Quantity + "$.");
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.print("| №  |  ");
        System.out.print("Импульс, %  |  ");
        System.out.print("Тейк-профит, %  |  ");
        System.out.print("Доход, %  |  ");
        System.out.print("Количество свечей  |  ");
        System.out.print("В плюс  |  ");
        System.out.print("В минус  |  ");
        System.out.println("Пропущено |");
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        w = 0;
        while (w<5) {
            System.out.print("| " + (w+1) + "  |   ");
            System.out.print(String.format("%.1f", BestImpulseValueUpPercentage [w]) + "        |  ");
            System.out.print(String.format("%.1f", BestTakeProfitPercentage [w]) + "             |  ");
            System.out.print(String.format("%.1f", BestPossibleIncome [w]) + "       |  ");
            System.out.print(NumberOfCandlesForAnalysis + "                 |  ");
            System.out.print(BestDealsInPlus [w] + "      |  ");
            System.out.print(BestDealsInMinus [w] + "        |  ");
            System.out.println(DealMissed [w] + "        |");
            System.out.println("--------------------------------------------------------------------------------------------------------------");
            w++;
        }
        System.out.println("Продолжить работу?");
        System.out.println("1 - да, выбрать № параметров.");
        System.out.println("2 - да, ввести свои параметры.");
        System.out.println("3 - нет.");
        Scanner scanner = new Scanner(System.in);
        String Answer = scanner.nextLine();
        if (Answer.equals("1")) {
            System.out.print("Введите № параметров (от 1 до 5): ");
            int Answer2 = Integer.parseInt (scanner.nextLine());
            if (Answer2 < 1 || Answer2 > 5) {
                GetInfo.FuncGetDateTime();
                System.out.println("Введено неверное значение.");
                System.out.println("Завершение работы.");
                System.out.println("----------------------------------------");
                System.exit(0);
            }
            ImpulseValueUpPercentage = BestImpulseValueUpPercentage [Answer2-1];
            TakeProfitPercentage = BestTakeProfitPercentage [Answer2-1];
            GetInfo.FuncGetDateTime();
            System.out.println("Продолжение работы.");
            System.out.println("----------------------------------------");
        } else {
            if (Answer.equals("2")) {
                System.out.print("Процент импульса: ");
                ImpulseValueUpPercentage = Double.parseDouble(scanner.nextLine());
                System.out.print("Процент тейк-профита: ");
                TakeProfitPercentage = Double.parseDouble(scanner.nextLine());
                GetInfo.FuncGetDateTime();
                System.out.println("Продолжение работы.");
                System.out.println("----------------------------------------");
            } else {
                GetInfo.FuncGetDateTime();
                System.out.println("Завершение работы.");
                System.out.println("----------------------------------------");
                System.exit(0);
            }
        }
        return new double[] {
                TakeProfitPercentage,
                ImpulseValueUpPercentage,
                ImpulseValueDownPercentage,
                DeviationToStopLossPercentage
        };
    }
}
