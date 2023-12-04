import SwiftUI
import kotlin

@main
struct iOSApp: App {
    init() {
        KotlinHelperKt.startKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            ListView()            
        }
    }
}
