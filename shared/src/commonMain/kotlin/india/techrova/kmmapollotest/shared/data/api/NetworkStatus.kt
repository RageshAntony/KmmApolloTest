package india.techrova.kmmapollotest.shared.data.api


sealed class NetworkStatus {

    data class Loading(var loading: Boolean) : NetworkStatus()

    data class CustomSignal(var signal: String) : NetworkStatus()

    data class CustomSignalDetailed(var signal: ErrorCaseData) : NetworkStatus()

    data class Failure(val e: Throwable) : NetworkStatus()

    data class Data<T> (val data: T ) : NetworkStatus()

    companion object {

        fun loading(isLoading: Boolean): NetworkStatus = Loading(isLoading)

        fun customstatus(signal: String): NetworkStatus = CustomSignal(signal)

        fun customStatusDetailed(signals: ErrorCaseData): NetworkStatus = CustomSignalDetailed(signals)

        fun failure(e: Throwable): NetworkStatus = Failure(e)

        fun <T> data(data: T): NetworkStatus = Data(data)
    }

}