folder('Tools') {
    description('Folder for miscellaneous tools.')
}

job('Tools/clone-repository') {
    description('Clones a repository.')
    parameters {
        stringParam('GIT_REPOSITORY_URL', '', 'Git URL of the repository to clone')
    }
    wrappers {
        preBuildCleanup { // Clean before build
            includePattern('**/target/**')
            deleteDirectories()
            cleanupParameter('CLEANUP')
        }

    }
    steps {
        shell('git clone ${GIT_REPOSITORY_URL}')
    }
//     triggers {
//         manualTrigger()
//     }
// }
}

job('Tools/SEED') {
    description('Seed job for creating new jobs.')
    parameters {
        stringParam('GITHUB_NAME', '', 'GitHub repository owner/repo_name (e.g.: "EpitechIT31000/chocolatine')
        stringParam('DISPLAY_NAME', '', 'Display name for the job')
    }
    steps {
        dsl {
            external('job_dsl.groovy')
        }
    }
    triggers {
        manualTrigger()
    }
}