import SwiftUI
import KMPNativeCoroutinesAsync
import KMPObservableViewModelSwiftUI
import Shared

struct ListView: View {
    @StateViewModel
    var viewModel = ListViewModel(
        museumRepository: KoinDependencies().museumRepository
    )

    let columns = [
        GridItem(.adaptive(minimum: 120), alignment: .top)
    ]

    var body: some View {
        ZStack {
            if !viewModel.objects.isEmpty {
                NavigationStack {
                    ScrollView {
                        LazyVGrid(columns: columns, alignment: .leading, spacing: 20) {
                            ForEach(viewModel.objects, id: \.self) { item in
                                NavigationLink(destination: DetailView(objectId: item.objectID)) {
                                    ObjectFrame(obj: item, onClick: {})
                                }
                                .buttonStyle(PlainButtonStyle())
                            }
                        }
                        .padding(.horizontal)
                    }
                }
            } else {
                Text("No data available")
            }
        }
    }
}

struct ObjectFrame: View {
    let obj: MuseumObject
    let onClick: () -> Void

    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            GeometryReader { geometry in
                AsyncImage(url: URL(string: obj.primaryImageSmall)) { phase in
                    switch phase {
                    case .empty:
                        ProgressView()
                            .frame(width: geometry.size.width, height: geometry.size.width)
                    case .success(let image):
                        image
                            .resizable()
                            .scaledToFill()
                            .frame(width: geometry.size.width, height: geometry.size.width)
                            .clipped()
                            .aspectRatio(1, contentMode: .fill)
                    default:
                        EmptyView()
                            .frame(width: geometry.size.width, height: geometry.size.width)
                    }
                }
            }
            .aspectRatio(1, contentMode: .fit)

            Text(obj.title)
                .font(.headline)

            Text(obj.artistDisplayName)
                .font(.subheadline)

            Text(obj.objectDate)
                .font(.caption)
        }
    }
}
