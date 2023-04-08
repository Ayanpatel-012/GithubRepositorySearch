package com.example.searchgitrepo.Utils

import com.example.searchgitrepo.Models.Repository

object RepositoryComparators {
    val starsComparator: Comparator<Repository> = Comparator { repo1, repo2 ->
        repo2.stars.compareTo(repo1.stars)
    }

    val watchersComparator: Comparator<Repository> = Comparator { repo1, repo2 ->
        repo2.watchers.compareTo(repo1.watchers)
    }

    val scoreComparator: Comparator<Repository> = Comparator { repo1, repo2 ->
        repo2.score.compareTo(repo1.score)
    }

    val nameComparator: Comparator<Repository> = Comparator { repo1, repo2 ->
        repo1.name.compareTo(repo2.name)
    }

    val createdAtComparator: Comparator<Repository> = Comparator { repo1, repo2 ->
        repo2.createdAt.compareTo(repo1.createdAt)
    }

    val updatedAtComparator: Comparator<Repository> = Comparator { repo1, repo2 ->
        repo2.updatedAt.compareTo(repo1.updatedAt)
    }
}

