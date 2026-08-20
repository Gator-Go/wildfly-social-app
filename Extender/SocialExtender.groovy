/**
 * This code extends the basic build to include new files and new code.
 */

class MyData {
    String theFile
    String extMarker
    String srcInsert
}

def changes = []

// ******** New code changes ********

// none

// ******** Process code changes ********
def normalize(String text) {
    text.replaceAll(/\r\n|\r/, "\n")
}

def processFiles(File dir, List<MyData> changes) {
    dir.eachFileRecurse { file ->
        if (!file.isFile()) return

        changes.each { data ->
            if (file.name == data.theFile) {
                def oldText = normalize(file.text)
                def marker  = normalize(data.extMarker)
                def insert  = normalize(data.srcInsert)
                def newText = oldText.replace(marker, insert)

                file.write(newText)

                if (!newText.contains(insert)) {
                    println "${data.theFile} missing changes"
                }
            }
        }
    }
}

processFiles(new File("../social"), changes)

// ******** Include new files ********

// Copy logo
new File("../social_logo.png").withInputStream { src ->
    new File("../social/social-war/src/main/webapp/resources/gfx/logo.png").withOutputStream { dst ->
        dst << src
    }
}
