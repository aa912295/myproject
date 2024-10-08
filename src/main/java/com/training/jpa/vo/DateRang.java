package com.training.jpa.vo;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
public class DateRang {
	private LocalDateTime startDate;
	private LocalDateTime endDate;
}
