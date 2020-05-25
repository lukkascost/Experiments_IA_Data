using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace ExperimentsData.Migrations
{
    public partial class newrulesForSamples : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Samples_Datasets_DatasetEntityId",
                table: "Samples");

            migrationBuilder.DropIndex(
                name: "IX_Samples_DatasetEntityId",
                table: "Samples");

            migrationBuilder.AlterColumn<string>(
                name: "OriginalFileName",
                table: "Samples",
                type: "TEXT",
                nullable: false,
                oldClrType: typeof(string),
                oldType: "TEXT",
                oldNullable: true);

            migrationBuilder.AlterColumn<Guid>(
                name: "DatasetEntityId",
                table: "Samples",
                type: "TEXT",
                nullable: false,
                oldClrType: typeof(Guid),
                oldType: "TEXT",
                oldNullable: true);

            migrationBuilder.AddUniqueConstraint(
                name: "AK_Samples_DatasetEntityId_order",
                table: "Samples",
                columns: new[] { "DatasetEntityId", "order" });

            migrationBuilder.AddUniqueConstraint(
                name: "AK_Samples_DatasetEntityId_OriginalFileName",
                table: "Samples",
                columns: new[] { "DatasetEntityId", "OriginalFileName" });

            migrationBuilder.AddForeignKey(
                name: "FK_Samples_Datasets_DatasetEntityId",
                table: "Samples",
                column: "DatasetEntityId",
                principalTable: "Datasets",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Samples_Datasets_DatasetEntityId",
                table: "Samples");

            migrationBuilder.DropUniqueConstraint(
                name: "AK_Samples_DatasetEntityId_order",
                table: "Samples");

            migrationBuilder.DropUniqueConstraint(
                name: "AK_Samples_DatasetEntityId_OriginalFileName",
                table: "Samples");

            migrationBuilder.AlterColumn<string>(
                name: "OriginalFileName",
                table: "Samples",
                type: "TEXT",
                nullable: true,
                oldClrType: typeof(string),
                oldType: "TEXT");

            migrationBuilder.AlterColumn<Guid>(
                name: "DatasetEntityId",
                table: "Samples",
                type: "TEXT",
                nullable: true,
                oldClrType: typeof(Guid),
                oldType: "TEXT");

            migrationBuilder.CreateIndex(
                name: "IX_Samples_DatasetEntityId",
                table: "Samples",
                column: "DatasetEntityId");

            migrationBuilder.AddForeignKey(
                name: "FK_Samples_Datasets_DatasetEntityId",
                table: "Samples",
                column: "DatasetEntityId",
                principalTable: "Datasets",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);
        }
    }
}
