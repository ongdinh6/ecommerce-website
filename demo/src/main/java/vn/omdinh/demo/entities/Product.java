package vn.omdinh.demo.entities;

import lombok.Getter;
import lombok.Setter;
import org.jooq.TableOptions;

@Getter
@Setter
public class Product {
    private String qualifier;
    private TableOptions options;

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public void setOptions(TableOptions options) {
        this.options = options;
    }
}
