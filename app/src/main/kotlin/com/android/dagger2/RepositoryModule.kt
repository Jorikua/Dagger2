package com.android.dagger2

import com.android.dagger2.data.Repository
import com.android.dagger2.data.RepositoryImpl
import com.android.dagger2.data.remote.ApiHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun providesGithubRepository(apiHelper: ApiHelper): RepositoryImpl {
        return Repository(apiHelper)
    }
}