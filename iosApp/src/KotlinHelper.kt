import com.jetbrains.kmpapp.KoinDependencies
import com.jetbrains.kmpapp.di.initKoin
import com.jetbrains.kmpapp.screens.DetailViewModel
import com.jetbrains.kmpapp.screens.ListViewModel


private val koinDependencies = KoinDependencies()

fun createListViewModel() = ListViewModel(koinDependencies.museumRepository)

fun createDetailViewModel() = DetailViewModel(koinDependencies.museumRepository)

fun startKoin() = initKoin()
