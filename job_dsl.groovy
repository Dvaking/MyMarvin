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
    triggers {
        manualTrigger()
    }
}