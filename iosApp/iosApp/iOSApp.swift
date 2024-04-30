import SwiftUI
import Shared

@main
struct iOSApp: App {
    init() {
        KoinKt.doInitKoin(modules: [])
    }
    
    var body: some Scene {
        WindowGroup {
            ListView()            
        }
    }
}
