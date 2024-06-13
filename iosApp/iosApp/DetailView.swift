import Foundation
import SwiftUI
import Shared
import KMPNativeCoroutinesAsync
import KMPObservableViewModelSwiftUI

struct DetailView: View {
    @StateViewModel
    var viewModel = DetailViewModel(
        museumRepository: KoinDependencies().museumRepository
    )

    let objectId: Int32

    var body: some View {
        VStack {
            if let obj = viewModel.museumObject {
                ObjectDetails(obj: obj)
            }
        }
        .onAppear {
            viewModel.setId(objectId: objectId)
        }
    }
}

struct ObjectDetails: View {
    var obj: MuseumObject

    var body: some View {
        ScrollView {

            VStack {
                AsyncImage(url: URL(string: obj.primaryImageSmall)) { phase in
                    switch phase {
                    case .empty:
                        ProgressView()
                    case .success(let image):
                        image
                            .resizable()
                            .scaledToFill()
                            .clipped()
                    default:
                        EmptyView()
                    }
                }

                VStack(alignment: .leading, spacing: 6) {
                    Text(obj.title)
                        .font(.title)

                    LabeledInfo(label: "Artist", data: obj.artistDisplayName)
                    LabeledInfo(label: "Date", data: obj.objectDate)
                    LabeledInfo(label: "Dimensions", data: obj.dimensions)
                    LabeledInfo(label: "Medium", data: obj.medium)
                    LabeledInfo(label: "Department", data: obj.department)
                    LabeledInfo(label: "Repository", data: obj.repository)
                    LabeledInfo(label: "Credits", data: obj.creditLine)
                }
                .padding(16)
            }
        }
    }
}

struct LabeledInfo: View {
    var label: String
    var data: String

    var body: some View {
        Spacer()
        Text("**\(label):** \(data)")
    }
}
