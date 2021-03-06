package com.med.feature.entries.di

import com.med.feature.entries.HealthTimelineFragment
import com.med.utilization.di.AppComponent
import dagger.Component

@Component(
	dependencies = [AppComponent::class],
	modules = [EntriesViewModelModule::class, EntryModule::class]
)
@EntriesScope
interface EntriesComponent {
	fun inject(fragment: HealthTimelineFragment)
}

