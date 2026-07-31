import SwiftUI
import KotlinModules

@main
struct iOSApp: App {
    init() {
        KoinKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ListView()
        }
    }
}
