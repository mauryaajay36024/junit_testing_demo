package com.healthycoderapp;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BMICalculatorTest {
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("This is used to create connections and to start server");
	}
	
	@AfterAll
	static void afterall() {
		System.out.println("This is use to close the connections");
	}

	@Test
	void should_ReturnTrue_When_DietRecommended() {
		
		//given
		double weight=89;
		double height=1.6;
		
		//when
		boolean recommended=BMICalculator.isDietRecommended(weight, height);
		
		//then
		assertTrue(recommended);
		
	}
	@Test
	void should_ReturnFalse_When_DietNotRecommended() {
		//given
		double weight=60;
		double height=2.6;
		
		//when
		boolean recommended= BMICalculator.isDietRecommended(weight, height);
		
		//then
		assertFalse(recommended);
	}
	
	@Test
	void should_ThrowsException_When_HeightZero() {
		//given
		double weight=60;
		double height=0.0;
		
		//when
		Executable executable=() -> BMICalculator.isDietRecommended(weight, height);
		
		//then
		assertThrows(ArithmeticException.class,executable);
	}
	@Test
	void should_ReturnCoderWithWorstBMI_When_CoderListNotEmpty() {
		
		//given
		List<Coder> coders=new ArrayList<>();
		coders.add(new Coder(1.80,60.0));
		coders.add(new Coder(1.82,98.0));
		coders.add(new Coder(1.83,64.0));
		
		//when
		Coder coderWorstBMI =BMICalculator.findCoderWithWorstBMI(coders);
		
		// then
		assertAll(
			() ->assertEquals(1.82,coderWorstBMI.getHeight()),
			() ->assertEquals(98.0, coderWorstBMI.getWeight())
		);
	}
	
	@Test
	void should_ReturnNull_WhenCoderListIsEmpty() {
		//given
		List<Coder> coders=new ArrayList<>();
		
		//when
		Coder coderWithWorstBMI=BMICalculator.findCoderWithWorstBMI(coders);
		
		//then
		assertNull(coderWithWorstBMI);
	}
	
	@Test
	void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty() {
		//given
		List<Coder> coders=new ArrayList<>();
		coders.add(new Coder(1.80,60.0));
		coders.add(new Coder(1.82,98.0));
		coders.add(new Coder(1.82,64.7));
		double [] expected = {18.52,29.59,19.53};
		
		//when
		double[] bmiScores=BMICalculator.getBMIScores(coders);
		
		//then
		assertArrayEquals(expected, bmiScores);
		
	}
	
	@ParameterizedTest
	@ValueSource(doubles = {89.1,95.0,110.0})
	void should_ReturnTrue_When_DietRecommended(Double coderWeight) {
		
		// Given
		double weight=coderWeight;
		double height=1.72;
		
		//When
		boolean recommended=BMICalculator.isDietRecommended(weight, height);
		
		// Then
		assertTrue(recommended);
		
	}
	
	@ParameterizedTest(name = "weight={0},height={1}")
	@CsvSource(value= {"89.0,1.72","95.0,1.75,110.0,1.78"})
	void should_ReturnTrue_When_DietRecommended(Double coderWeight,Double coderHeight) {
		//give
		double weight=coderWeight;
		double height=coderHeight;
		
		// when
		boolean recommended=BMICalculator.isDietRecommended(weight, height);
		
		//then
		assertTrue(recommended);
		
	}


}
