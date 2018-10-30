package com.etcbase.mockito.service;

import static org.hamcrest.CoreMatchers.notNullValue;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.etcbase.mockito.EtcBase;
import com.etcbase.mockito.dao.EtcBaseDao;

public class EtcBaseSpyServiceTest {

	// Mocking and Injecting our mocks dependencies.

	@Spy
	private EtcBaseDao etcBaseDao;

	@InjectMocks
	private EtcBaseService etcBaseService;

	@Captor
	private ArgumentCaptor<EtcBase> etcBaseArgumentCaptor;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * 	We want to verify the interact of our service with the real
	 * 	EtcBaseDao service.
	 */
	@Test
	public void shouldSaveAndReturnNewBranch() {

		// Prepare the objects you need.
		final EtcBase etcBase = new EtcBase();
		
		// We call our real(spy) service
		final EtcBase savedEtcBase = etcBaseService.save(etcBase);

		// Assert
		Assert.assertThat(savedEtcBase, CoreMatchers.is(notNullValue()));
		
		// Verify it has been called
		Mockito.verify(etcBaseDao).save(Mockito.any(EtcBase.class));
		Mockito.verify(etcBaseDao, Mockito.times(1)).checkId(etcBase);
		Mockito.verify(etcBaseDao, Mockito.never()).delete(Mockito.anyLong());
	}

}
