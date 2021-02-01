package india.techrova.kmmapollotest.shared.data.api.repo.service

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloExperimental
import com.apollographql.apollo.network.http.ApolloHttpNetworkTransport
import india.techrova.kmmapollotest.GetAllCategoriesQuery
import india.techrova.kmmapollotest.shared.data.api.ErrorCaseData
import india.techrova.kmmapollotest.shared.data.api.NetworkStatus
import india.techrova.kmmapollotest.shared.data.api.repo.CategoryRepo
import india.techrova.kmmapollotest.shared.utils.CFlow
import india.techrova.kmmapollotest.shared.utils.wrap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class CategoryService : CategoryRepo {
    @ExperimentalCoroutinesApi
    @ApolloExperimental
    val apolloClient = ApolloClient(
        networkTransport = ApolloHttpNetworkTransport(
            serverUrl = "https://tough-toucan-42.hasura.app/v1/graphql",
            headers = mapOf(
                "Accept" to "application/json",
                "Content-Type" to "application/json",
            )
        )
    )
    //j
    @ApolloExperimental
    override suspend fun getAllCategories(): CFlow<NetworkStatus<List<GetAllCategoriesQuery.Category>>> {
        return flow<NetworkStatus<List<GetAllCategoriesQuery.Category>>> {
            apolloClient.query(GetAllCategoriesQuery()).execute().collect {
                if (it.hasErrors()) {
                   emit( NetworkStatus.customStatusDetailed<List<GetAllCategoriesQuery.Category>>(ErrorCaseData(404, "Apollo Error", "APP")))
                }
                else {
                    emit(NetworkStatus.data<List<GetAllCategoriesQuery.Category>>(it.data?.category as List<GetAllCategoriesQuery.Category> ) )
                }
            }
        }.wrap()
    }
}