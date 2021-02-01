package india.techrova.kmmapollotest.shared.data.api

import india.techrova.kmmapollotest.GetAllCategoriesQuery


sealed class NetworkStatus<T> {

    data class Loading<T>(var loading: Boolean) : NetworkStatus<T> ()

    data class CustomSignal<T>(var signal: String) : NetworkStatus<T>()

    data class CustomSignalDetailed<T>(var signal: ErrorCaseData) : NetworkStatus<T>()

    data class Failure<T>(val e: Throwable) : NetworkStatus<T>()

    data class Data<T> (val data: T ) : NetworkStatus<T>()

    companion object {

        fun <T>loading(isLoading: Boolean): NetworkStatus<T> = Loading(isLoading)

        fun <T> customstatus(signal: String): NetworkStatus<T> = CustomSignal(signal)

        fun <T> customStatusDetailed(signals: ErrorCaseData): NetworkStatus<T> = CustomSignalDetailed(signals)

        fun <T> failure(e: Throwable): NetworkStatus<T> = Failure(e)

        fun <T> data(data: T): NetworkStatus<T> = Data<T>(data)
    }

}