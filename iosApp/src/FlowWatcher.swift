import KotlinModules

// Replace with Swift Export once available
class FlowWatcher: Kotlinx_coroutines_coreFlowCollector {
    let onEach: (Any?) -> Void

    init(onEach: @escaping (Any?) -> Void) {
        self.onEach = onEach
    }

    func emit(value: Any?) async throws {
        onEach(value)
    }
}
