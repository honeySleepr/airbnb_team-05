//
//  LocationViewCell.swift
//  Airbnb
//
//  Created by Jason on 2022/06/10.
//

import UIKit

final class LocationViewCell: UITableViewCell {
    
    static let identifier = "LocationViewCell"
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        appendSubView()
        setupLocationCellLayout()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError()
    }
    
    private let locationImage: UIImageView = {
        let locationImage = UIImageView()
        locationImage.image = UIImage(named: "cityBox")
        return locationImage
    }()
    
    private let locationTitle: UILabel = {
        let locationTitle = UILabel()
        locationTitle.textColor = .systemGray
        locationTitle.font = .systemFont(ofSize: 17)
        return locationTitle
    }()
    
    func configure(location: String?) {
        locationTitle.text = location
    }
    
    private func appendSubView() {
        contentView.addSubview(locationImage)
        contentView.addSubview(locationTitle)
    }
    
    private func setupLocationCellLayout() {
        locationImage.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            locationImage.topAnchor.constraint(equalTo: contentView.topAnchor),
            locationImage.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 20),
            locationImage.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -10)
        ])
        
        locationTitle.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            locationTitle.topAnchor.constraint(equalTo: contentView.topAnchor),
            locationTitle.leadingAnchor.constraint(equalTo: locationImage.trailingAnchor, constant: 20),
            locationTitle.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -10)
        ])
    }
}
