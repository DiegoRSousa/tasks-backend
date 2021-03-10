package br.ce.wcaquino.taskbackend.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void deveRetornarTrueParaDatasFuturas() {
		LocalDate date = LocalDate.of(2030, 10, 10);
		boolean result = DateUtils.isEqualOrFutureDate(date);
		assertTrue(result);
	}
	
	@Test
	public void deveRetornarFalseParaDatasFuturas() {
		LocalDate date = LocalDate.of(2010, 10, 10);
		boolean result = DateUtils.isEqualOrFutureDate(date);
		assertFalse(result);
	}
	
	@Test
	public void deveRetornarTrueParaDataAtual() {
		LocalDate date = LocalDate.now();
		boolean result = DateUtils.isEqualOrFutureDate(date);
		assertTrue(result);
	}

}
