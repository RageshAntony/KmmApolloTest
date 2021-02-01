package india.techrova.kmmapollotest.shared.data.api.repo

import india.techrova.kmmapollotest.GetAllCategoriesQuery
import india.techrova.kmmapollotest.shared.data.api.NetworkStatus
import kotlinx.coroutines.flow.Flow

interface CategoryRepo {
   suspend fun getAllCategories() : Flow<NetworkStatus<List<GetAllCategoriesQuery.Category>>>
}