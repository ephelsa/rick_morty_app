package com.github.ephelsa.rickandmorty.resourceoption.infraestructure

import com.github.ephelsa.rickandmorty.shared.data.NetworkResource
import com.github.ephelsa.rickandmorty.resourceoption.data.ResourceOptionDataSource
import com.github.ephelsa.rickandmorty.resourceoption.domain.ResourceOption
import com.github.ephelsa.rickandmorty.shared.infraestructure.httpclient.NetworkResourceHandler
import kotlinx.coroutines.delay

class ResourceOptionRepositoryDataSource : ResourceOptionDataSource {

    override suspend fun getAllResourceOptions(): NetworkResource<List<ResourceOption>> {
        return NetworkResourceHandler.handleResponse { allResourceOptionsFakeData() }
    }

    private suspend fun allResourceOptionsFakeData(): List<ResourceOption> {
        val characters = ResourceOption(
            "https://miro.medium.com/max/3840/1*j55XGyX4ZGimd0mjGeYXQQ.png",
            "Characters",
            "Know all about your favorites characters!"
        )

        val episodes = ResourceOption(
            "https://lh3.googleusercontent.com/proxy/wCDCyorydskM61XelvJNGEult0fKzGmx0ygp2q8ICGSv-Ri8gwmGEtwpUxS1hVwWWkd_rQw3FzYOY1ZaxI66UT3EDC_Dfa3BJwm_87zl08VPAEehSuOuWK0Vufjhs_JdogCxDqzkLQqBjvbWGKJT-BrqYx-iutEbzoylm_3X8UafUPNuld2FjjhySDf9vk-FjWBycAZoCYziM7fO18cYlIYoIZ9daYQ0P4zN",
            "Episodes",
            "How many time did you waste watching this?"
        )

        val locations = ResourceOption(
            "https://filmdaily.co/wp-content/uploads/2018/06/rick-and-morty-screaming-sun-1024x475.jpg",
            "Locations",
            "You have been stay there... in your nightmares or wet dreams."
        )

        delay(2000)

        return listOf(characters, episodes, locations)
    }
}