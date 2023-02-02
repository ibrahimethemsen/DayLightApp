package com.daylightapp.data.repository.weather

import app.cash.turbine.test
import com.daylightapp.common.NetworkResult
import com.daylightapp.data.Constants.LAT
import com.daylightapp.data.Constants.LON
import com.daylightapp.data.TestResponseEnum
import com.daylightapp.data.mapper.CurrentWeatherMapperImpl
import com.daylightapp.data.mapper.DetailFiveDayWeatherMapperImpl
import com.daylightapp.data.mapper.FiveDayWeatherMapperImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class WeatherRepositoryTest {
    //TODO @Mock oluşturamıyor
    private lateinit var currentWeatherMapperImpl: CurrentWeatherMapperImpl

    @Mock
    private lateinit var fiveDayWeatherMapperImpl: FiveDayWeatherMapperImpl

    @Mock
    private lateinit var detailFiveDayWeatherMapperImpl: DetailFiveDayWeatherMapperImpl

    private lateinit var fakeWeatherRepositoryImpl: FakeWeatherRepositoryImpl

    @Before
    fun setup() {
        currentWeatherMapperImpl = CurrentWeatherMapperImpl()
        MockitoAnnotations.openMocks(this)
        fakeWeatherRepositoryImpl = FakeWeatherRepositoryImpl(
            fiveDayWeatherMapperImpl,
            detailFiveDayWeatherMapperImpl,
            currentWeatherMapperImpl
        )
    }

    @Test
    fun when_getFiveDay_is_loading()=runBlocking{
        fakeWeatherRepositoryImpl.getFiveDayThreeHoursWeatherForecast(LAT,LON).test {
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Loading::class.java)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun when_getFiveDay_is_success()=runBlocking{
        fakeWeatherRepositoryImpl.setRequest(TestResponseEnum.SUCCESS)
        fakeWeatherRepositoryImpl.getFiveDayThreeHoursWeatherForecast(LAT,LON).test {
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Success::class.java)
            awaitComplete()
        }
    }

    @Test
    fun when_getFiveDay_is_error()=runBlocking{
        fakeWeatherRepositoryImpl.setRequest(TestResponseEnum.ERROR)
        fakeWeatherRepositoryImpl.getFiveDayThreeHoursWeatherForecast(LAT,LON).test {
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Error::class.java)
            awaitComplete()
        }
    }
    @Test
    fun when_getDetailFiveDay_is_loading()=runBlocking{
        fakeWeatherRepositoryImpl.getFiveDayDetailWeatherForeCast(LAT,LON).test {
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Loading::class.java)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun when_getDetailFiveDay_is_success()=runBlocking{
        fakeWeatherRepositoryImpl.setRequest(TestResponseEnum.SUCCESS)
        fakeWeatherRepositoryImpl.getFiveDayDetailWeatherForeCast(LAT,LON).test {
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Success::class.java)
            awaitComplete()
        }
    }
    @Test
    fun when_getCurrentDay_is_success()=runBlocking{
        fakeWeatherRepositoryImpl.setRequest(TestResponseEnum.SUCCESS)
        fakeWeatherRepositoryImpl.getCurrentWeather(LAT,LON).test {
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Success::class.java)
            awaitComplete()
        }
    }
    @Test
    fun when_getDetailFiveDay_is_error()=runBlocking{
        fakeWeatherRepositoryImpl.setRequest(TestResponseEnum.ERROR)
        fakeWeatherRepositoryImpl.getFiveDayDetailWeatherForeCast(LAT,LON).test {
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Error::class.java)
            awaitComplete()
        }
    }
    @Test
    fun when_getCurrentDay_is_loading()=runBlocking{
        fakeWeatherRepositoryImpl.getCurrentWeather(LAT,LON).test {
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Loading::class.java)
            cancelAndIgnoreRemainingEvents()
        }
    }



    @Test
    fun when_getCurrentDay_is_error()=runBlocking{
        fakeWeatherRepositoryImpl.setRequest(TestResponseEnum.ERROR)
        fakeWeatherRepositoryImpl.getCurrentWeather(LAT,LON).test {
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Loading::class.java)
            assertThat(awaitItem()).isInstanceOf(NetworkResult.Error::class.java)
            awaitComplete()
        }
    }

}