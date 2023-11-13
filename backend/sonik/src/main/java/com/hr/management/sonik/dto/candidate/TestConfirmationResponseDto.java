package com.hr.management.sonik.dto.candidate;

import com.hr.management.sonik.entity.Test;
import lombok.Data;

@Data
public class TestConfirmationResponseDto {
    private Test test;
    private Integer duration;
}
