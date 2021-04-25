package br.com.lukkascost.commons.module.models.enuns;

public enum SplitType {
    by_percent_total,
    by_percent_per_class,
    by_quantity;


    public interface TypeConstants {
        String by_percent_total = "by_percent_total";
        String by_percent_per_class = "by_percent_per_class";
        String by_quantity = "by_quantity";
    }
}
