package com.etcbase.mockito.service;

import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.Random;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.etcbase.mockito.EtcBase;
import com.etcbase.mockito.dao.EtcBaseDao;

public class EtcBaseServiceTest {

	@Mock
	private EtcBaseDao etcBaseDao;

	@InjectMocks
	private EtcBaseService etcBaseService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Using Mockito.when() and thenReturn() of Mockito to tests our EtcBaseService
	 * save method.
	 */
	@Test
	public void shouldSaveAndReturnNewBranch() {

		// Prepare the objects you need.
		final EtcBase etcBase = new EtcBase(); 

		// when
		Mockito.when(etcBaseDao.save(Mockito.any())).thenReturn(etcBase); // then

		// Mockito.doReturn(etcBaseService).when(etcBaseService).save(etcBase);

		// We then call our service
		final EtcBase savedEtcBase = etcBaseService.save(etcBase);

		// Assert
		Assert.assertThat(savedEtcBase, CoreMatchers.is(notNullValue()));
	}
	
	/**
	 * 	Throwing exception from a void method
	 */
	@Test(expected = RuntimeException.class)
	public void shouldThrowRuntimExceptionWhenDeleteIsCalled() {
		
		Mockito.doThrow(RuntimeException.class).when(etcBaseDao).delete(Mockito.anyLong());
		
		etcBaseService.delete(new Random().nextLong());
	}
}
