package kg.management.datum.core.model.service;

import lombok.Getter;

@Getter
public enum Code {
    A('A'), B('B'), C('C'), D('D'), L('L'), P('P'), R('R'), T('T'),
    ;
    private final char code;

    Code(char code) {
        this.code = code;
    }
}
